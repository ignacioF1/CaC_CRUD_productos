const url = 'http://localhost:8080/producto';
const urlproductos= 'http://localhost:8080/productos';

// Crea un nuevo Producto
export const nuevoProducto = async cliente => {

    try {
        await fetch(url, {
            method: 'POST',
            body: JSON.stringify( cliente ),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        window.location.href = 'index.html';
    } catch (error) {
        console.log(error);
    }
}


// Obtiene todos los Productos
export const obtenerProductos = async()=>{

    try {
        const resultado = await fetch(urlproductos);
        const productos = await resultado.json();
        
        return productos;
    } catch (error) {
        console.log(error)
    }
}
// Elimina un Producto...
export const eliminarProducto = async id => {
    
    try {
       
        await fetch(`${url}/${id}`, {
            method: 'DELETE'
        });
    } catch (error) {
        console.log(error);
    }
}

// Obtiene un Producto...
export const obtenerProducto = async id => {
    
    try {
        const resultado = await fetch(`${url}/${id}`, {
            method: 'GET'});
        const producto = await resultado.json();
        return producto;
    } catch (error) {
        console.log(error);
    }
}

// Edita Producto
export const editaProducto = async cliente => {

    try {
        await fetch(url, {
            method: 'PUT',
            body: JSON.stringify( cliente ),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        //window.location.href = 'index.html';
    } catch (error) {
        console.log(error);
    }
}

