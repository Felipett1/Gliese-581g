package co.entidades;

import co.entidades.ReabrirActividades;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-27T09:27:58")
@StaticMetamodel(Causas.class)
public class Causas_ { 

    public static volatile SingularAttribute<Causas, BigDecimal> id;
    public static volatile SingularAttribute<Causas, String> nombre;
    public static volatile ListAttribute<Causas, ReabrirActividades> reabrirActividadesList;
    public static volatile SingularAttribute<Causas, String> descripcion;

}