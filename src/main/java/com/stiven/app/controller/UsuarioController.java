package com.stiven.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stiven.app.entity.HabitacionEntity;
import com.stiven.app.entity.ResidenciaEntity;
import com.stiven.app.service.HabitacionService;
import com.stiven.app.service.IResidenciaService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IResidenciaService residenciaService;
	
	@Autowired
    private HabitacionService habitacionService; // Inyecta el servicio HabitacionService

	@GetMapping("/ver-habitaciones/{residenciaId}")
    public String getHabitacionesPorResidencia(@PathVariable Long residenciaId, Model model) {
        ResidenciaEntity residencia = residenciaService.listById(residenciaId);
        List<HabitacionEntity> habitacionesDisponibles = habitacionService.getHabitacionesDisponiblesPorResidencia(residencia);
        model.addAttribute("residencia", residencia);
        model.addAttribute("habitaciones", habitacionesDisponibles);
        return "/usuario/ver-habitaciones"; // Nombre de la vista
    }

	
}
