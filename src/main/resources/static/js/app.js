import {  obtenerProductos, eliminarProducto } from './API.js';

(function () {
    const listado = document.querySelector('#listado-productos');

    document.addEventListener('DOMContentLoaded', mostrarProductos);

    listado.addEventListener('click', confirmarEliminar);

    async function mostrarProductos() {
        const productos = await obtenerProductos();

        productos.forEach(producto => {
            const { nombre, descripcion, marca, precio, id } = producto;
           
            const row = document.createElement('tr');

            row.innerHTML += `
                <td class="px-6 py-2 whitespace-no-wrap border-b border-gray-200 gray">
                    <p class="text-sm leading-5 font-medium text-gray-700 text-lg  font-bold"> ${nombre} </p>
                    <p class="text-sm leading-10 text-gray-700"> ${descripcion} </p>
                </td>
                <td class="px-6 py-2 whitespace-no-wrap border-b border-gray-200 gray">
                    <p class="text-gray-700">${marca}</p>
                </td>
                <td class="px-6 py-2 whitespace-no-wrap border-b border-gray-200  leading-5 text-gray-700 gray">    
                    <p class="text-gray-600">${'$'+ precio}</p>
                </td>
                <td class="px-6 py-2 whitespace-no-wrap border-b border-gray-200 text-sm leading-5 gray">
                    <a class="text-teal-600 hover:text-teal-900 mr-5">Editar</a>
                    <a href="#" data-producto="${id}" class="text-red-600 hover:text-red-900 eliminar">Eliminar</a>
                </td>
            `;

            listado.appendChild(row);
        });
    }

    function confirmarEliminar(e) {
        if (e.target.classList.contains('eliminar')) {
            const ProductoId = e.target.dataset.producto;
            
			$(document).ready(function(){
    		$("#eliminarModal").modal("show");
  			});

            $("#eliminar").click(function(){
                $("#eliminarModal").modal("hide");
                eliminarProducto(ProductoId);
                $("#response").html("¡Eliminado correctamente!").show();
    			setTimeout(function(){
        		$("#response").hide();
        		location.reload();
        		},2000);
        	});
            }
        }
    
    
})();           