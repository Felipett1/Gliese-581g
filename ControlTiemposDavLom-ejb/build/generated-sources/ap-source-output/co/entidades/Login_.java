package co.entidades;

import co.entidades.Empleados;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-27T09:27:58")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile SingularAttribute<Login, BigDecimal> id;
    public static volatile SingularAttribute<Login, String> nameUser;
    public static volatile SingularAttribute<Login, Empleados> idEmpleado;
    public static volatile SingularAttribute<Login, String> password;
    public static volatile SingularAttribute<Login, String> administrado;

}