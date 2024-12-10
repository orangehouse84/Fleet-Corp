/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarioDisponibilidad {

    public String generarCalendario(List<vehiculo> vehiculos, LocalDate fechaInicio, LocalDate fechaFin) {
        StringBuilder html = new StringBuilder();

        // Crear calendaerio
        html.append("<div class='calendario'>");
        html.append("  <input type='text' id='calendario-input' name='calendario' readonly>");
        html.append("</div>");

        // Configurar flatpickr
        html.append("<script>");
        html.append("  flatpickr('#calendario-input', {");
        html.append("    mode: 'range',");
        html.append("    dateFormat: 'd/m/Y',");
        html.append("    minDate: 'today',");
        html.append("    defaultDate: ['").append(fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("', '").append(fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("'],"); 
        html.append("    disable: [");

        // Deshabilitar las fechas en las que los vehículos no están disponibles
        for (vehiculo vehiculo : vehiculos) {
            if (!vehiculo.getEstado().equals("Disponible")) {
                html.append("{");
                html.append("  from: '").append(fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("',");
                html.append("  to: '").append(fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("',");
                html.append("},");
            }
        }

        html.append("    ],");
        html.append("    onChange: function(selectedDates, dateStr, instance) {");
        html.append("      // Actualizar la disponibilidad de los vehículos al cambiar las fechas");
        html.append("      // ...");
        html.append("    }");
        html.append("  });");
        html.append("</script>");

        return html.toString();
    }
}

