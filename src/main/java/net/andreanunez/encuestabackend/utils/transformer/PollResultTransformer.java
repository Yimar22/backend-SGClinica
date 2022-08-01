package net.andreanunez.encuestabackend.utils.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.andreanunez.encuestabackend.interfaces.PollResult;
import net.andreanunez.encuestabackend.models.responses.PollResultRest;
import net.andreanunez.encuestabackend.models.responses.ResultDetailRest;

//vamos a transformar desde una lista de PollResult a una Lista PollResultRest
public class PollResultTransformer implements Transformer<List<PollResult>, List<PollResultRest>> {

    @Override
    public List<PollResultRest> transformData(List<PollResult> data) {

        // estamos utilizando un mapa porque vamos a utilizar como key el questionId y
        // despues tener un objeto con todas
        // las respuestas, que va a ser PollResultRest(con las pregunta y los detalles)
        Map<String, PollResultRest> transformedData = new HashMap<String, PollResultRest>();

        for (PollResult result : data) {
            PollResultRest pollResult;
            String key = Long.toString(result.getQuestionId());

            // si no existe la key dentro del mapa la creamos
            if (!transformedData.containsKey(key)) {
                List<ResultDetailRest> details = new ArrayList<ResultDetailRest>();
                details.add(new ResultDetailRest(result.getAnswer(), result.getResult()));
                pollResult = new PollResultRest(result.getQuestion(), details);
                transformedData.put(key, pollResult);

                // si ya existia la key, la seleccionamos y agregamos el resultado de la
                // respuesta
            } else {
                pollResult = transformedData.get(key);
                pollResult.getDetails().add(new ResultDetailRest(result.getAnswer(), result.getResult()));
            }
        }

        // quitamos la key
        List<PollResultRest> resultList = new ArrayList<>(transformedData.values());
        return resultList;
    }

}
