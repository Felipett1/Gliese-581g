package co.entidades;

import co.entidades.Rol;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-08-03T08:41:38")
@StaticMetamodel(Recurso.class)
public class Recurso_ { 

    public static volatile SingularAttribute<Recurso, BigDecimal> id;
    public static volatile SingularAttribute<Recurso, String> nombre;
    public static volatile SingularAttribute<Recurso, Long> identificacion;
    public static volatile SingularAttribute<Recurso, String> primerApellido;
    public static volatile SingularAttribute<Recurso, Rol> rol;
    public static volatile SingularAttribute<Recurso, String> segundoApellido;

}