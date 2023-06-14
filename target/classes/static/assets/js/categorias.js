window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki
    cargarCategorias();
    const categorias = document.getElementById('categorias');
    if (categorias) {
        new simpleDatatables.DataTable(categorias);
    }
});

async function cargarCategorias(){


      const request = await fetch('categorias', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }

      });
      const categorias = await request.json();

      console.log(categorias);
    let listadoHTML = '';


     for(let categoria of categorias){
         let botonEliminar = '<a href="#" onclick="eliminarCategoria('+ categoria.codCategoria +')" class="btn btn-danger">    <i class="fas fa-trash-alt"></i></a>';
         let categoriaHTML = ' <tr> <th scope="row">'+ categoria.codCategoria +'</th> <td>'+ categoria.nombre_categoria +'</td> <td>  <a href="#" class="btn btn-primary"> <i class="fas fa-marker"></i> </a>'+ botonEliminar +'  </td> </tr>';
         listadoHTML += categoriaHTML;
     }

             const categoriasTbody = document.querySelector("#categorias tbody");


                if (categoriasTbody) {
                  categoriasTbody.outerHTML =  listadoHTML;
                } else {
                console.error("El elemento '#categorias tbody' no se ha encontrado.");
                }

}

async function eliminarCategoria(id){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }
const request = await fetch('eliminar/' + id, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }

      });
        location.reload();
}
