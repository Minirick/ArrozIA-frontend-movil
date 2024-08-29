from fastapi import HTTPException, Depends, status
from sqlalchemy.orm import Session
import src.schemas.schemas as schemas
import src.models.permissionModel as permissionModel
from src.database.database import get_session
from src.models.rolModel import UsuarioFincaRol, Rol
from src.models.permissionModel import Permission, RolPermiso 

def createPermission(permission: schemas.CreatePermission, session: Session = Depends(get_session)):
    newPermission = permissionModel.Permission(nombre=permission.name, descripcion=permission.description)
    session.add(newPermission)
    session.commit()
    session.refresh(newPermission)
    return {
        "id": newPermission.id,
        "name": newPermission.nombre,
        "description": newPermission.descripcion,
    }

def getPermission(permission_id: int, session: Session = Depends(get_session)):
    permission = session.query(permissionModel.Permission).filter(permissionModel.Permission.id == permission_id).first()
    if permission is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Permission not found")
    
    # Convertir manualmente el objeto a un diccionario compatible con el esquema
    return {
        "id": permission.id,
        "name": permission.nombre,
        "description": permission.descripcion,
    }

def updatePermission(permission_id: int, permission_update: schemas.UpdatePermission, session: Session = Depends(get_session)):
    permission = session.query(permissionModel.Permission).filter(permissionModel.Permission.id == permission_id).first()
    if permission is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Permission not found")

    if permission_update.name:
        permission.nombre = permission_update.name
    if permission_update.description:
        permission.descripcion = permission_update.description

    session.commit()
    session.refresh(permission)
    
    # Convertir manualmente el objeto a un diccionario compatible con el esquema
    return {
        "id": permission.id,
        "name": permission.nombre,
        "description": permission.descripcion,
    }

def deletePermission(permission_id: int, session: Session = Depends(get_session)):
    permission = session.query(permissionModel.Permission).filter(permissionModel.Permission.id == permission_id).first()
    if permission is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Permission not found")

    session.delete(permission)
    session.commit()
    return {"message": "Permission deleted successfully"}

def check_permission(user_id: int, permission_name: str, db: Session):
    user_roles = db.query(UsuarioFincaRol).filter(UsuarioFincaRol.usuario_id == user_id).all()
    if not user_roles:
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN,
            detail="User does not have any roles"
        )

    for user_role in user_roles:
        role = db.query(Rol).filter(Rol.id == user_role.rol_id).first()
        if not role:
            continue
        
        permission = db.query(Permission).join(RolPermiso).filter(
            RolPermiso.rol_id == role.id,
            Permission.nombre == permission_name  # Aquí se usa 'nombre' en lugar de 'name'
        ).first()

        if permission:
            return True

    raise HTTPException(
        status_code=status.HTTP_403_FORBIDDEN,
        detail="Insufficient permissions"
    )
