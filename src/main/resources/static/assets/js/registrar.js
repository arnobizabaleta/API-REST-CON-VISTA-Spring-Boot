window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    //OnReady

});

async function registrarCategoria(){

    let datos = {};

    datos.nombre_categoria = document.getElementById('txtNombre').value;

      const request = await fetch('guardarCategoria', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
         body: JSON.stringify(datos)

      });


   window.location.href = 'index.html'

      //console.log(categorias);

}

