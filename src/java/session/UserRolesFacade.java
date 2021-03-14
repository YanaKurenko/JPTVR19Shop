/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lenovo
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {
    
    @PersistenceContext(unitName = "JPTVR19ShopPU")
    private EntityManager em;
    
    @EJB private RoleFacade roleFacade;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public boolean isRole(String roleName, User user) {
        try {
            UserRoles userRoles = (UserRoles) em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.role.roleName = :roleName AND userRoles.user = :user")
                    .setParameter("roleName", roleName)
                    .setParameter("user", user)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTopRoleForUser(User user) {
        List<UserRoles> listUserRoles = em.createQuery("SELECT userRoles FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", user)
                .getResultList();
        for(int i=0;i<listUserRoles.size();i++){
            if("ADMIN".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "ADMIN";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("MANAGER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "MANAGER";
            }
        }
        for(int i=0;i<listUserRoles.size();i++){
            if("BUYER".equals(listUserRoles.get(i).getRole().getRoleName())){
                return "BUYER";
            }
        }
        return "-";
    }

    public void setNewRole(UserRoles userRoles) {
        this.em.createQuery("DELETE FROM UserRoles userRoles WHERE userRoles.user = :user")
                .setParameter("user", userRoles.getUser())
                .executeUpdate();
        UserRoles ur;
        if("ADMIN".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("ADMIN"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
        if("MANAGER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("MANAGER"));
            this.create(ur);
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
        if("BUYER".equals(userRoles.getRole().getRoleName())){
            ur = new UserRoles(userRoles.getUser(),roleFacade.findByName("BUYER"));
            this.create(ur);
        }
    }
    
}