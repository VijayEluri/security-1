package org.intalio.tempo.security.ws;

import static org.intalio.tempo.security.ws.Constants.OM_FACTORY;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.axiom.om.OMElement;
import org.intalio.tempo.security.Property;
import org.intalio.tempo.security.authentication.AuthenticationException;
import org.intalio.tempo.security.rbac.ObjectNotFoundException;
import org.intalio.tempo.security.rbac.RBACException;
import org.intalio.tempo.security.rbac.RoleNotFoundException;
import org.intalio.tempo.security.rbac.UserNotFoundException;

public class RBACQueryResponseMarshaller {
    public static OMElement getAssignedUsersResponse(String[] users) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ASSIGNED_USERS_RESPONSE);

        for (int i = 0; i < users.length; i++) {
            OMElement user = OM_FACTORY.createOMElement(RBACQueryConstants.USER, response);
            user.setText(users[i]);
        }

        return response;
    }

    public static OMElement getAssignedRolesResponse(String[] roles) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ASSIGNED_ROLES_RESPONSE);
        for (int i = 0; i < roles.length; i++) {
            OMElement role = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE, response);
            role.setText(roles[i]);
        }
        return response;
    }

    public static OMElement getRoleOperationsOnObjectResponse(String[] operations) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE_OPERATIONS_ON_OBJECT_RESPONSE);
        for (int i = 0; i < operations.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.OPERATION, response);
            operation.setText(operations[i]);
        }
        return response;
    }

    public static OMElement getUserOperationsOnObjectResponse(String[] operations) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.USER_OPERATIONS_ON_OBJECT_RESPONSE);

        for (int i = 0; i < operations.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.OPERATION, response);
            operation.setText(operations[i]);
        }

        return response;
    }

    public static OMElement getAuthorizedUsersResponse(String[] users) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.AUTHORIZED_USERS_RESPONSE);

        for (int i = 0; i < users.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.USER, response);
            operation.setText(users[i]);
        }

        return response;
    }

    public static OMElement getAuthorizedRolesResponse(String[] roles) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.AUTHORIZED_ROLES_RESPONSE);

        for (int i = 0; i < roles.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE, response);
            operation.setText(roles[i]);
        }

        return response;
    }

    public static OMElement getTopRolesResponse(String[] roles) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.TOP_ROLES_RESPONSE);

        for (int i = 0; i < roles.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE, response);
            operation.setText(roles[i]);
        }

        return response;
    }

    public static OMElement getAscendantRolesResponse(String[] roles) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ASCENDANT_ROLES_RESPONSE);

        for (int i = 0; i < roles.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE, response);
            operation.setText(roles[i]);
        }

        return response;
    }

    public static OMElement getDescendantRolesResponse(String[] roles) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.DESCENDANT_ROLES_RESPONSE);

        for (int i = 0; i < roles.length; i++) {
            OMElement operation = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE, response);
            operation.setText(roles[i]);
        }

        return response;
    }

    public static OMElement getUserPropertiesResponse(Property[] properties) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.GET_USER_PROPERTIES_RESPONSE);

        for (int i = 0; i < properties.length; i++) {
            OMElement property = OM_FACTORY.createOMElement(RBACQueryConstants.PROPERTY, response);
            OMElement name = OM_FACTORY.createOMElement(RBACQueryConstants.NAME, property);
            OMElement value = OM_FACTORY.createOMElement(RBACQueryConstants.VALUE, property);
            value.setText((String) properties[i].getValue());
            name.setText((String) properties[i].getName());
        }

        return response;
    }

    public static OMElement getRoleAndUserResponse(
            Map<String, Map<String, Property[]>> roleDetails) {
        OMElement responseElement = OM_FACTORY
                .createOMElement(RBACQueryConstants.GET_ROLES_USERS_RESPONSE);

        for (Entry<String, Map<String, Property[]>> role : roleDetails
                .entrySet()) {
            String roleName = role.getKey();
            Map<String, Property[]> roleProeprties = role.getValue();

            OMElement roleElement = OM_FACTORY.createOMElement(
                    RBACQueryConstants.ROLE, responseElement);
            OMElement roleNameElement = OM_FACTORY.createOMElement(
                    RBACQueryConstants.NAME, roleElement);

            roleNameElement.setText(roleName);

            for (Entry<String, Property[]> user : roleProeprties.entrySet()) {
                String userName = user.getKey();
                Property[] userProperties = user.getValue();

                OMElement userElement = OM_FACTORY.createOMElement(
                        RBACQueryConstants.USER, roleElement);
                OMElement userNameElement = OM_FACTORY.createOMElement(
                        RBACQueryConstants.NAME, userElement);

                userNameElement.setText(userName);

                for (Property userProperty : userProperties) {
                    String propertyName = userProperty.getName();
                    String propertyValue = (String) userProperty.getValue();

                    OMElement propertyElement = OM_FACTORY.createOMElement(
                            RBACQueryConstants.PROPERTY, userElement);
                    OMElement propertyNameElement = OM_FACTORY.createOMElement(
                            RBACQueryConstants.NAME, propertyElement);
                    OMElement propertyValueElement = OM_FACTORY
                            .createOMElement(RBACQueryConstants.VALUE,
                                    propertyElement);

                    propertyValueElement.setText(propertyValue);
                    propertyNameElement.setText(propertyName);
                }
            }
        }

        return responseElement;
    }

    public static OMElement getRolePropertiesResponse(Property[] properties) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.GET_ROLE_PROPERTIES_RESPONSE);

        for (int i = 0; i < properties.length; i++) {
            OMElement property = OM_FACTORY.createOMElement(RBACQueryConstants.PROPERTY, response);
            OMElement name = OM_FACTORY.createOMElement(RBACQueryConstants.NAME, property);
            OMElement value = OM_FACTORY.createOMElement(RBACQueryConstants.VALUE, property);
            value.setText((String) properties[i].getValue());
            name.setText((String) properties[i].getName());
            response.addChild(property);
        }

        return response;
    }

    public static OMElement getRBACExceptionResponse(RBACException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.RBAC_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }

    public static OMElement getRemoteExceptionResponse(RemoteException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.REMOTE_EXCEPTION);
        response.setText(e.getMessage());
        return response;

    }

    public static OMElement getUserNotFoundExceptionResponse(UserNotFoundException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.USER_NOT_FOUND_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }

    public static OMElement getRoleNotFoundExceptionResponse(RoleNotFoundException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ROLE_NOT_FOUND_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }

    public static OMElement getObjectNotFoundExceptionResponse(ObjectNotFoundException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.OBJECT_NOT_FOUND_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }

    public static OMElement getIllegalArgumentException(IllegalArgumentException e) {
        OMElement response = OM_FACTORY.createOMElement(RBACQueryConstants.ILLEGAL_ARGUMENT_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }

    public static OMElement getAuthenticationExceptionResponse(
            AuthenticationException e) {
        OMElement response = OM_FACTORY
                .createOMElement(RBACQueryConstants.AUTHENTICATION_EXCEPTION);
        response.setText(e.getMessage());
        return response;
    }
}
