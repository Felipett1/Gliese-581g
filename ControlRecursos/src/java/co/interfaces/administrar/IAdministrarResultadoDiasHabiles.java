/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.interfaces.administrar;

import co.entidades.ResultadoDiasHabiles;
import java.math.BigDecimal;
import java.util.Date;

public interface IAdministrarResultadoDiasHabiles {

    public ResultadoDiasHabiles resultadoDiasHabiles(Date fechaInicial, BigDecimal numeroHoras, Long IdEmpleado);
}
