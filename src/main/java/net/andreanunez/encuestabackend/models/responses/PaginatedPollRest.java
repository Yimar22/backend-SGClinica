package net.andreanunez.encuestabackend.models.responses;

import java.util.List;

import lombok.Data;

//Modelo que se le devuelve cuando se haga la petición de traer todas las encuestas
@Data
public class PaginatedPollRest {

    // lista de todas las encuestas
    private List<PollRest> polls;

    // cuantas páginas hay en total
    private int totalPages;

    // Total de los registros
    private long totalRecords;

    // cuantos elementos tiene la página actual
    private long currentPageRecords;

    // pagina actual
    private int currentPage;
}
