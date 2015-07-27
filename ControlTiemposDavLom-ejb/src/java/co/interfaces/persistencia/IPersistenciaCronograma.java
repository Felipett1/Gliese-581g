/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.Cronograma;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IPersistenciaCronograma {
    public List<Cronograma> estadoCronograma(BigInteger id);
    public List<Cronograma> cronogramaGeneral();
    public Cronograma estadisticasCronogramaGeneral();
}
