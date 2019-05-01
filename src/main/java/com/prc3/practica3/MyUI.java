package com.prc3.practica3;

import javax.servlet.annotation.WebServlet;

import org.w3c.dom.Document;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    
    //----------------LAYOUT ACTUALIZAR / MODIFICAR PRODUCTO--------------------------//
    final VerticalLayout layoutAct = new VerticalLayout();
    
    final TextField name_act = new TextField();
    name_act.setCaption("Introduzca nombre del producto que se desea actualizar:");
    
    final TextField precio_act = new TextField();
    precio_act.setCaption("Introduzca precio a actualizar:");
    
    final TextField cantidad_act = new TextField();
    cantidad_act.setCaption("Introduzca cantidad a actualizar:");
    
    final TextField descripcion_act = new TextField();
    descripcion_act.setCaption("Introduzca descripcion a actualizar:");

    Button button_act = new Button("MODIFICAR PRODUCTO");
    button_act.addClickListener(e -> {
    	UpdateJava actu = new UpdateJava();
    	// String name, String precio_act, String cantidad_act, String descripcion_act
    	actu.UpdateXml(name_act.getValue(), precio_act.getValue(), cantidad_act.getValue(), descripcion_act.getValue());
    	layoutAct.addComponent(new Label ("PRODUCTO ACTUALIZADO CORRECTAMENTE"));
    });

    layoutAct.addComponents(name_act, precio_act, cantidad_act, descripcion_act, button_act);
    
    
    //----------------LAYOUT CREAR PRODUCTO--------------------------//
	final VerticalLayout layoutCreate = new VerticalLayout();
	    
    final TextField name_Create = new TextField();
    name_Create.setCaption("Introduzca nombre del producto a crear:");
    
    final TextField precio_Create = new TextField();
    precio_Create.setCaption("Introduzca precio del nuevo producto:");
    
    final TextField cantidad_Create = new TextField();
    cantidad_Create.setCaption("Introduzca cantidad del nuevo producto:");
    
    final TextField descripcion_Create = new TextField();
    descripcion_Create.setCaption("Introduzca descripcion del producto:");

    Button button_Create = new Button("CREAR PRODUCTO");
    button_Create.addClickListener(e -> {
    	CreateJava create = new CreateJava();
    	// String name, String precio_act, String cantidad_act, String descripcion_act
    	create.AnadirProducto(name_Create.getValue(), precio_Create.getValue(), cantidad_Create.getValue(), descripcion_Create.getValue());
    	// en un futuro de debera realizar un return en la funcion para saber si existe o no el producto que se quiere crear
    	layoutCreate.addComponent(new Label ("PRODUCTO CREADO CORRECTAMENTE"));
    });
    
    
    layoutCreate.addComponents(name_Create, precio_Create, cantidad_Create, descripcion_Create, button_Create);
    //----------------LAYOUT LEER XML--------------------------//
    final VerticalLayout layoutRead = new VerticalLayout();
    
    Button button_Read = new Button("LEER ARCHIVO XML");
    button_Read.addClickListener(e-> {
    	ReadXML leer = new ReadXML();
    	String archivo = leer.LeerXML();
    	Notification.show(archivo);
    });
    layoutRead.addComponents(button_Read);
    
    
    //----------------LAYOUT BORRAR PRODUCTO--------------------------//
    final VerticalLayout layoutBor = new VerticalLayout();
    
    final TextField name_bor = new TextField();
    name_bor.setCaption("Introduzca nombre del producto que se desea borrar:");
    
    Button button_bor = new Button("BORRAR PRODUCTO");
    button_bor.addClickListener(e -> {
    	Delete_Update borr = new Delete_Update();
    	// String name, String precio_act, String cantidad_act, String descripcion_act
    	borr.Borrado_Update(name_bor.getValue());
    	layoutBor.addComponent(new Label ("PRODUCTO BORRADO CORRECTAMENTE"));
    });
    
    layoutBor.addComponents(name_bor,button_bor);
    
    //----------------SET CONTENT--------------------------//

    setContent(layoutBor);
    
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}



