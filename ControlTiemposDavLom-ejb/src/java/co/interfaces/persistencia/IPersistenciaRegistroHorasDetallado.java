/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.persistencia;

import co.entidades.RegistroHorasDetallado;
import java.util.List;

/**
 *
 * @author 908036
 */
public interface IPersistenciaRegistroHorasDetallado {
    public List<RegistroHorasDetallado> consultarDetalleRegistroHoras(String condiciones);
}
