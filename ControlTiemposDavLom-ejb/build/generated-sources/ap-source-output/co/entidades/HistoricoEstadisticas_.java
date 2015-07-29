package co.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2015-07-29T14:34:29")
@StaticMetamodel(HistoricoEstadisticas.class)
public class HistoricoEstadisticas_ { 

    public static volatile SingularAttribute<HistoricoEstadisticas, BigInteger> id;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigDecimal> ev;
    public static volatile SingularAttribute<HistoricoEstadisticas, Date> fecha;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigDecimal> spi;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigDecimal> ac;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigDecimal> pv;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigInteger> proyecto;
    public static volatile SingularAttribute<HistoricoEstadisticas, BigDecimal> cpi;

}