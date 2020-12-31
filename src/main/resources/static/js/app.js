import { obtenerProductos, eliminarProducto, obtenerProducto, editaProducto } from './API.js';
import { mostrarAlerta, validar } from './funciones.js';

//Mensajes
const debeIngreEdit = "¡Debe ingresar  para editar!";
const editado = "¡Editado correctamente!";
const debeIngreEli = "¡Debe ingresar  para eliminar!";
const eliCorre = "¡Eliminado correctamente!";


	(function() {
		const listado = document.querySelector('#listado-productos');

		document.addEventListener('DOMContentLoaded', mostrarProductos);

		listado.addEventListener('click', realizarAcciones);

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
                    <p class="text-gray-600">${'$' + precio}</p>
                </td>
                <td class="px-6 py-2 whitespace-no-wrap border-b border-gray-200 text-sm leading-5 gray">
                    <a href="#" data-producto="${id}" class="text-teal-600 hover:text-teal-900 mr-5 editar">Editar</a>
                    <a href="#" data-producto="${id}" class="text-red-600 hover:text-red-900 eliminar">Eliminar</a>
                </td>
            `;

				listado.appendChild(row);
			});
		}

		function realizarAcciones(e) {
		
			// Click en ELIMINAR
			if (e.target.classList.contains('eliminar')) {
				const ProductoId = e.target.dataset.producto;

				$(document).ready(function() {
					$("#eliminarModal").modal("show");
				});

				$("#eliminar").click(function() {
					$("#eliminarModal").modal("hide");
					eliminarProducto(ProductoId);
					if ($("#currentUser").text() == "Usuario: Invitado") {
						$("#response").html(debeIngreEli).show();
					} else {
						$("#response").html(eliCorre).show();
					}
					setTimeout(function() {
						$("#response").hide();
						location.reload();
					}, 2000);
				});
			}

			// Click en EDITAR
			if (e.target.classList.contains('editar')) {

				const ProductoId = e.target.dataset.producto;

				mostrarProducto();
				//Cargar los datos del producto a editar

				async function mostrarProducto() {
					// console.log("aqui");
					const producto = await obtenerProducto(ProductoId);
					producto => { const { nombre, descripcion, marca, precio, cantidad, rubro, id } = producto; }
					$("#id").val(producto.id);
					$("#nombre").val(producto.nombre);
					$("#descripcion").val(producto.descripcion);
					$("#marca").val(producto.marca);
					$("#precio").val(producto.precio);
					$("#cantidad").val(producto.cantidad);
					$("#rubro").val(producto.rubro);
				}

				$("#editarModal").modal("show");

				$("#editar").click(function() {

					validarProducto();

					function validarProducto() {
						const id = document.querySelector('#id').value;
						const nombre = document.querySelector('#nombre').value;
						const descripcion = document.querySelector('#descripcion').value;
						const marca = document.querySelector('#marca').value;
						const precio = document.querySelector('#precio').value;
						const cantidad = document.querySelector('#cantidad').value;
						const rubro = document.querySelector('#rubro').value;

						const producto = {
							id,
							nombre,
							descripcion,
							marca,
							precio,
							cantidad,
							rubro
						};

						if (validar(producto)) {
							// Mostrar mensaje
							mostrarAlerta('Todos los campos son obligatorios');
							return;
						}


						$("#editarModal").modal("hide");
						editaProducto(producto);
						if ($("#currentUser").text() == "Usuario: Invitado") {
							$("#response").html(debeIngreEdit).show();
						} else {
							$("#response").html(editado).show();
						}
						setTimeout(function() {
							$("#response").hide();
							location.reload();
						}, 2000);
					}

				});

			}

		}

	})();           