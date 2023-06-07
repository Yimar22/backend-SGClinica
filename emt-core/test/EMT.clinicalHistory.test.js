const { StatusCodes } = require('http-status-codes');
const { expect } = require('chai');
const axios = require('axios');
const dotenv = require("dotenv");

describe('History', () => {

    let authToken;

    before(async () => {

        const credentials = {

            username: 'admin',
            password: 'testpassword',
        };

        const response = await axios.post('http://localhost:8080/public/auth/login', credentials);
        expect(response.status).to.equal(StatusCodes.OK);
        expect(response.status).to.equal(200);
        expect(response.data).to.have.property('token');

        authToken = response.data.token;
    });

    it('debería crear un nuevo formato de historia clinica a un paciente', async () => {

        const history = {


          "name": "Historia clinica urgencias NUEVO",
          "description": "En caso de baleados",
          "enabled": 1,
          "payload": "{ \"msg\" : \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc pulvinar dolor faucibus nibh efficitur maximus. Nullam sit amet diam augue. Nullam non faucibus leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis vulputate metus lectus, iaculis cursus risus dictum sollicitudin. Proin eleifend nibh id lorem congue, ac commodo ex ultrices. Nunc non rhoncus nibh. Maecenas nibh tortor, lacinia sed tincidunt vitae, viverra non ante. Nullam placerat nibh ut nibh tempor pretium. Nunc lacinia semper nulla, vitae elementum magna volutpat in. Etiam accumsan mi quis auctor suscipit. Suspendisse id malesuada nisi, vel tincidunt libero. Suspendisse cursus sem in purus maximus semper. Maecenas ut suscipit felis.Etiam sagittis tellus eleifend, lobortis turpis at, semper ex. Ut ultrices euismod scelerisque. Proin nisi arcu, pellentesque sed nisl vel, consectetur finibus sapien. Vivamus luctus, lorem venenatis imperdiet sagittis, sem leo dictum est, quis eleifend ipsum libero sed mi. In non sem sem. Etiam cursus dolor interdum magna placerat tempus. Vivamus nec maximus tortor. Maecenas dignissim neque et tristique placerat. Morbi vel hendrerit quam, sed hendrerit ex. Donec ultrices ut orci eget cursus.Donec varius nisi eget ultrices fringilla. Duis lectus nisl, porttitor quis placerat id, bibendum a ex. Donec faucibus luctus euismod. Nunc bibendum sagittis sapien, at interdum sem malesuada pulvinar. Vivamus viverra tristique quam ut consequat. Fusce varius consectetur ipsum, non egestas purus accumsan eget. Pellentesque congue augue et ipsum tempor luctus. Morbi pretium odio vitae erat maximus, in mollis justo convallis. Integer maximus luctus dui sit amet blandit. Sed eu leo consectetur, dictum lectus et, hendrerit mi.Etiam pretium tortor ut odio consectetur, a tempus nulla vehicula. Nulla facilisi. Nulla egestas sem eu velit hendrerit ultrices. Maecenas quis euismod magna. Pellentesque non ex nulla. Proin dapibus elit vitae magna aliquam efficitur. Proin sagittis suscipit vehicula. Suspendisse urna nisl, mollis a eros ut, tempus tincidunt dui. Ut vitae blandit lectus, nec porta orci. Praesent pulvinar interdum lorem ac euismod. Aliquam vestibulum, dui eu elementum pretium, dolor felis pellentesque ante, non pharetra dolor odio a erat. Nunc ac ultricies lacus, sed cursus augue. Duis imperdiet lorem in ligula vestibulum, in eleifend lectus scelerisque.Sed at leo odio. Ut euismod bibendum aliquam. Aliquam facilisis ligula at sapien sollicitudin auctor. Donec laoreet lobortis lectus et molestie. Ut et fringilla augue. Aenean mattis fermentum augue quis pulvinar. Nulla in elit ornare, iaculis lorem id, ultricies risus. Aenean mattis varius sapien sit amet porta. Nam fringilla, magna vitae cursus mattis, arcu odio sagittis nulla, quis commodo libero eros at turpis. Aliquam sagittis iaculis vulputate. Nulla sed tincidunt erat, quis molestie lacus. Sed at lorem a neque dictum pharetra non ac nisi. Vivamus auctor hendrerit arcu. Nam ultricies molestie urna, fringilla eleifend justo facilisis at. Aliquam molestie sem finibus tellus rhoncus varius.Mauris a ex id nisl mollis rhoncus. Proin ut risus sit amet leo imperdiet rhoncus quis a nunc. Sed sed massa eget ex mollis tempor id quis eros. Proin pretium sem a arcu ultrices scelerisque. Sed eget magna nulla. Ut vel sapien non arcu hendrerit maximus. Duis non diam vel eros facilisis dignissim. Donec vitae varius dui. Pellentesque eget pellentesque tortor. Sed interdum justo turpis, at dapibus turpis aliquam eu. Vestibulum blandit aliquet lacus, eu aliquam diam tincidunt posuere. Nam vehicula mollis eleifend. Praesent tristique tellus vel bibendum cursus. Phasellus venenatis vulputate arcu, in laoreet est vehicula condimentum. Sed dignissim, odio ac finibus vehicula, augue erat posuere quam, luctus convallis mi ligula nec eros. Aenean pulvinar risus a magna eleifend elementum.Nunc ultricies metus eget accumsan interdum. Duis imperdiet dolor ante, at blandit enim dignissim eu. Integer nulla lectus, lobortis sit amet auctor vitae, pretium vitae mauris. Vestibulum a purus pharetra, blandit est at, venenatis orci. Vivamus ultricies mollis convallis. Nunc cursus dictum vestibulum. Suspendisse potenti. Mauris ultricies sit amet lacus a feugiat.Nulla dignissim congue dui ut pharetra. Aliquam in erat et ligula molestie cursus. Praesent ultricies cursus laoreet. Aenean ullamcorpe tellus lectus. Aliquam a ipsum purus. Pellentesque luctus dapibus malesuada. Etiam dapibus lectus ligula, sit amet tempor metus molestie vitae. Etiam maximus leo a pellentesque ullamcorper. Quisque imperdiet felis accumsan velit malesuada ornare. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur convallis euismod imperdiet. Aliquam erat volutpat. Pellentesque quis metus sed orci porta molestie. Curabitur egestas dui eu gravida aliquam. Quisque fermentum ac orci et dictum.Nam sed justo auctor, maximus risus in, varius elit. Pellentesque elementum odio et mauris laoreet pharetra. Integer rhoncus lorem sapien, quis tristique ex luctus et. In hac habitasse platea dictumst. Vivamus luctus ornare libero, ac vulputate sem convallis vel. Suspendisse consequat ipsum quis eros tristique efficitur. Duis egestas quam non nibh sollicitudin, sit amet elementum erat iaculis. Vestibulum vel felis eget lectus cursus condimentum sed non erat. Mauris facilisis finibus tellus, vitae facilisis enim dignissim nec. Fusce vel leo velit. Nulla dictum efficitur est ac consectetur. Cras efficitur, sapien vitae cursus sagittis, dui neque scelerisque sem, pulvinar ullamcorper magna odio sit amet ligula. Duis vitae interdum ipsum.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut quam tortor, consectetur convallis augue in, bibendum varius orci. Cras facilisis faucibus velit, id sodales erat dignissim sit amet. Etiam et posuere neque, eu mollis dolor. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam tincidunt enim ac nisl tincidunt lobortis. Etiam ut justo mollis, laoreet diam sit amet, interdum erat. Sed at gravida lorem. Nam malesuada dolor vitae nisi condimentum euismod. Nullam consectetur sapien eget ante fermentum, consectetur egestas nisl molestie.Nam in vehicula elit. In et turpis nec erat imperdiet fringilla quis et tortor. Curabitur nec convallis nulla, vel rhoncus enim. Ut id odio at arcu feugiat vestibulum. Cras vulputate tortor ut est gravida aliquam. Nullam tincidunt consequat justo, sed mattis lacus cursus vel. Donec porttitor ligula ultricies ipsum lacinia, a congue mauris interdum. Sed sodales, magna ac ullamcorper pretium, lacus dolor accumsan ipsum, eget eleifend eros risus ac urna.Donec dignissim libero ante, in dignissim ligula sollicitudin non. Proin ac erat enim. Sed quis imperdiet mi. Aliquam molestie tortor erat, a mollis velit feugiat et. Nam id massa luctus, hendrerit magna eget, aliquam augue. Etiam ullamcorper dui at tellus accumsan, at sagittis massa ullamcorper. Phasellus sollicitudin, neque at egestas rhoncus, urna nunc fermentum odio, a molestie lectus dolor eu magna. Integer rhoncus mattis velit quis tempor.Pellentesque sit amet massa non mauris cursus convallis in sit amet ipsum. Etiam sed vehicula justo. Cras tempus convallis blandit. Donec ipsum lorem, maximus quis dictum non, luctus nec ex. Nam lacinia urna eget nibh luctus, quis interdum nulla fringilla. Aliquam fermentum, nisi viverra gravida ullamcorper, justo sem consectetur eros, ut ullamcorper ex ligula dictum dui. Ut vehicula felis velit, quis tristique est pulvinar at. Duis id aliquet nisl. Morbi laoreet pretium orci, eget bibendum ligula accumsan at. Quisque in cursus nisl, eget aliquam mauris. Etiam auctor lectus eget vulputate pharetra. Ut nec faucibus libero. Aliquam scelerisque varius magna non ullamcorper.Aliquam fermentum nibh quis viverra rhoncus. Maecenas sit amet orci vitae leo lobortis lobortis. Sed aliquet commodo elit a malesuada. Vivamus aliquet augue sed ullamcorper aliquet. Pellentesque lacinia porta sodales. Integer aliquam nulla sed orci accumsan commodo. Pellentesque id congue sapien, in porttitor felis. Aenean mauris augue, tincidunt a placerat eget, laoreet vitae lorem. Duis lobortis sagittis velit vel consectetur. Ut ultricies, eros et varius varius, mauris sem lobortis nulla, in laoreet justo lacus vel nibh. In hac habitasse platea dictumst.Nulla turpis sapien, lobortis vel suscipit nec, consectetur ut ipsum. Sed facilisis mollis vulputate. Nunc pellentesque fringilla hendrerit. Nunc eget urna ut metus imperdiet ornare ut quis nibh. Nam quis orci vitae felis varius tincidunt. Nulla ut suscipit turpis. Nunc placerat mauris nec sodales luctus. Nunc sed justo sagittis, mattis odio in, auctor nisl. Fusce odio mi, finibus congue odio at, mattis fringilla nunc. Suspendisse potenti. Ut blandit odio pulvinar, cursus leo eget, consequat nisi. Integer mollis ut odio ac laoreet. Vivamus imperdiet diam felis, ac aliquam nisl pretium a. Morbi blandit, est ut vehicula faucibus, nibh libero scelerisque odio, ut iaculis velit erat non erat. Etiam eu tortor eleifend diam gravida convallis sit amet at lectus. Morbi vitae dui lorem. \"}"

        };

        const response = await axios.post(
              'http://localhost:8080/clinicalhistoryformat',
              history,
              {
                headers: {
                  Authorization: `Bearer ${authToken}`,
                },
              }
        );

        expect(response.status).to.equal(200);
    });

    it('debería listar todos los formatos', async () => {

        const response = await axios.get(
            'http://localhost:8080/clinicalhistoryformat',
            {
                headers: {

                    Authorization: `Bearer ${authToken}`,
                },
            }
        );

        expect(response.status).to.equal(200);
    });

    it('debería obtener los detalles de un formato ', async () => {
        const config = {
            headers: {
                Authorization: `Bearer ${authToken}`,
            },
        };

        const response = await axios.get('http://localhost:8080/clinicalhistoryformat/5', config);

        expect(response.status).to.equal(200);
        expect(response.data.description).to.equal("Historia clínica que contiene información general");
    });
});