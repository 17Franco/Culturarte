#!/bin/bash

configuracionlocal(){	
	ORIGEN="./src/main/resources/config.properties"
	DESTINO="$HOME/.Culturarte/config.properties"
	
	echo "Verificando carpeta de configuración..."
	
	if [ ! -d "$HOME/.Culturarte" ]; then
    	echo "No existe ~/.Culturarte. Creando..."
    	mkdir -p "$HOME/.Culturarte"
    	echo "Carpeta creada en $HOME/.Culturarte"
	else
	 	echo "Carpeta ~/.Culturarte ya existe."
	fi
	
	if [ ! -f "$ORIGEN" ]; then
		 echo "No se encontró el archivo fuente en: $ORIGEN"
		 exit 1
	fi
	
	echo "Copiando archivo de configuración..."
	cp -f "$ORIGEN" "$DESTINO"

	
	if [ $? -eq 0 ]; then
		 echo "Archivo copiado correctamente a: $DESTINO"
	else
		 echo "Error al copiar el archivo."
	fi

}

validar_host() {
    local valor_actual="$1"
    local valor_final

    while true; do
        read -p "[$valor_actual] Ingrese uno nuevo para actualizar: " nuevo
        valor_final=${nuevo:-$valor_actual}

        # Validar formato básico (IP o localhost)
        if [[ "$valor_final" =~ ^([0-9]{1,3}\.){3}[0-9]{1,3}$ || "$valor_final" == "localhost" ]]; then
            
            
            if ping -c 1 -W 1 "$valor_final" &>/dev/null; then
                echo "$valor_final"
                return 0
            else
                printf "\nEl host '%s' tiene formato válido pero no responde al ping.\n" "$valor_final" 1>&2
                read -p "¿Deseas usarlo de todos modos? [s/N]: " resp < /dev/tty 1>&2
                if [[ "$resp" =~ ^[sS]$ ]]; then
                    echo "$valor_final"
                    return 0
                fi
            fi

        else
            printf "\nHost inválido. Intenta de nuevo (ej: 192.168.0.10 o localhost).\n" 1>&2
        fi

        echo 
        sleep 0.3
    done
}
validar_port() {
	 local valor_actual="$1"
	 local valor_final

	 while true; do
		  read -p "[$valor_actual] Ingrese uno nuevo para actualizar: " nuevo
		  valor_final=${nuevo:-$valor_actual}

		  # Verificar que sea número entre 1 y 65535
		 if ! [[ "$valor_final" =~ ^[0-9]+$ ]] || [ "$valor_final" -lt 1 ] || [ "$valor_final" -gt 65535 ]; then
			  echo "Puerto inválido. Debe ser un número entre 1 y 65535." 1>&2
			  continue
		 fi

		 # Verificar si está en uso
		 if ss -tuln | grep -q ":$valor_final "; then
			  	echo " El puerto $valor_final está en uso. Elige otro." 1>&2
		 else
			   echo "$valor_final"
		      return 0
		 fi
		 
	 done
}
PersonalizarConfig(){
 	DESTINO="$HOME/.Culturarte/config.properties"
	
	host_actual=$(grep '^WEB_SERVICES_HOST=' "$DESTINO" | cut -d'=' -f2)
	
	port_actual=$(grep '^WEB_SERVICES_PORT=' "$DESTINO" | cut -d'=' -f2)
	
	portR_actual=$(grep '^WEB_SERVICES_PORTR=' "$DESTINO" | cut -d'=' -f2)
	
	webHost_actual=$(grep '^WEB_HOST=' "$DESTINO" | cut -d'=' -f2)
	
	webUser_actual=$(grep '^USER_HOST_WEB=' "$DESTINO" | cut -d'=' -f2)
	
	
	echo  "Host actual para servicios soap" 
	host_final=$(validar_host "$host_actual")
	 
	echo  "Puerto actual para servicios soap" 
	port_final=$(validar_port "$port_actual")
	
	echo  "Puerto actual para servicios rest" 
	portR_final=$(validar_port "$portR_actual")
	
	echo  "Host actual para servidor web"
	WebHost_final=$(validar_host "$webHost_actual") 
		
	
	if ! [[ $WebHost_final == "localhost" ]] ; then
		echo  "Usuario del host web"
		read -p "[$webUser_actual] ingrese un nuevo User para remplazar: " nuevo_UserWeb
		UserWeb_final=${nuevo_UserWeb:-$host_actual}
		sed -i "s/^USER_HOST_WEB=.*/USER_HOST_WEB=$UserWeb_final/" "$DESTINO" 
	fi
	
	sed -i "s/^WEB_SERVICES_HOST=.*/WEB_SERVICES_HOST=$host_final/" "$DESTINO"
	
	sed -i "s/^WEB_SERVICES_PORT=.*/WEB_SERVICES_PORT=$port_final/" "$DESTINO" 
	
	sed -i "s/^WEB_SERVICES_HOSTR=.*/WEB_SERVICES_HOSTR=$host_final/" "$DESTINO"
	
	sed -i "s/^WEB_SERVICES_PORTR=.*/WEB_SERVICES_PORTR=$portR_final/" "$DESTINO" 
	
	sed -i "s/^WEB_HOST=.*/WEB_HOST=$WebHost_final/" "$DESTINO" 
	
	
}
configuracionPersonalizada(){
	DESTINO="$HOME/.Culturarte/config.properties"
	ORIGEN="./src/main/resources/config.properties"
	#primero me fijo si existe modifico sino existe copio el por defecto y modifico 
	
	if [ ! -f "$DESTINO" ]; then
		 echo "Copiando archivo de configuración..."
		 cp -f "$ORIGEN" "$DESTINO"
		 PersonalizarConfig
 
	else
		 PersonalizarConfig
	fi
	
}

crearArchivoConfig(){
	while true; do
		 echo "=============================="
		 echo "        MENÚ PRINCIPAL"
		 echo "=============================="
		 echo "1) Crear Archivo de Configuracion modo local"
		 echo "2) Crear Archivo de Configuracion modo personalizado"
		 echo "3) Salir"
		 echo "------------------------------"
		 read -p "Elige una opción [1-3]: " opcion

		 case $opcion in
		     1)
		         echo
		         configuracionlocal
		        	;;
		     2)
		         configuracionPersonalizada
		        	;;
		         
			  3)
		         echo "Saliendo..."
		         break
		         ;;
		     *)
		         echo "Opción inválida."
		         read -p "Presiona Enter para continuar..."
		         ;;
		 esac
		 
	  		echo
		 	read -p "Presiona Enter para volver al menú..."
		 	clear
	done

}

DeployServidorWeb(){
	echo " Crear Archivo de Configuracion"
}

DeployServidorCentral(){
 	echo "2Crear Archivo de Configuracion"
}

DeployProject(){
	DeployServidorCentral
	DeployServidorWeb
}
while true; do
    clear
    echo "=============================="
    echo "        MENÚ PRINCIPAL"
    echo "=============================="
    echo "1) Crear Archivo de Configuracion"
    echo "2) DeployProjects"
    echo "3) Deploy Servidor Central"
    echo "4) Deploy Servidor Web"
    echo "5) Help"
    echo "6) Salir"
    echo "------------------------------"
    read -p "Elige una opción [1-6]: " opcion

    case $opcion in
        1)
            echo
            clear
            crearArchivoConfig
           	;;
        2)
        		clear
            DeployProject
           	;;
        3)
        		clear
            DeployServidorCentral 
            ;;
        4)	
        		clear
            DeployServidorWeb
            ;;
        5)
            echo "Help"
            ;;
            
		  6)
            echo "Saliendo..."
            break
            ;;
        *)
            echo "Opción inválida."
            read -p "Presiona Enter para continuar..."
            ;;
    esac
done


