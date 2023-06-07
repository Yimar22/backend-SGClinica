const { StatusCodes } = require('http-status-codes');
const { expect } = require('chai');
const axios = require('axios');
const dotenv = require("dotenv");

describe('Patient', () => {

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

    it('debería crear un nuevo paciente', async () => {

        const newPatient = {
            "id": "9898",
            "personalInformation": {
                "id": "9898",
                "firstName": "Camilone",
                "lastName": "Gutierrezne Cordobaen",
                "email": "kamneklogs@gmailne.com",
                "birthDate": "2000-11-18T21:03:52Z",
                "genderId": 2,
                "civilStatusId": 1,
                "phoneNumber": "3178981818",
                "address": "Calle 44 #109-80"
            },
            "nationalityState": {
                "nationality": "Colombia",
                "nationalityStateCode": 1,
                "nationalityStateName": "No importa"
            },
            "affiliationInformation": {
                "medicalEntity": "algo1",
                "healthRegime": "algo2",
                "benefitPlan": "algo3",
                "socialStratum": "algo4"
            },
            "admissionInformation": {
                "caretaker": "Carlosannsaass",
                "caretakerPhoneNumber": "176276",
                "admissionDate": "2022-12-14T01:20:23Z",
                "medicalConsultationReason": "Se cayó x4"
            }
        };

        const response = await axios.post(
              'http://localhost:8080/patient',
              newPatient,
              {
                headers: {
                  Authorization: `Bearer ${authToken}`,
                },
              }
        );

        expect(response.status).to.equal(200);
        expect(response.data.id).to.equal("9898");
        expect(response.data.personalInformation.firstName).to.equal("Camilone");

    });

    it('debería listar todos los pacientes', async () => {

        const response = await axios.get(
            'http://localhost:8080/patient',
            {
                headers: {

                    Authorization: `Bearer ${authToken}`,
                },
            }
        );

        expect(response.status).to.equal(200);
    });

    it('debería obtener los detalles de un paciente existente', async () => {
        const config = {
            headers: {
                Authorization: `Bearer ${authToken}`,
            },
        };

        const response = await axios.get('http://localhost:8080/patient/9898', config);

        expect(response.status).to.equal(200);
        expect(response.data.personalInformation.firstName).to.equal("Camilone");
    });

    it('debería actualizar un paciente', async () => {

        const newPatient = {

            "id": "9898",
            "personalInformation": {
                "id": "9898",
                "firstName": "DIEGO",
                "lastName": "TORRES",
                "email": "kamneklogs@gmailxxxxxne.com",
                "birthDate": "2000-11-18T21:03:52Z",
                "genderId": 2,
                "civilStatusId": 1,
                "phoneNumber": "3178981818",
                "address": "Calle 44 #109-80"
            },
            "nationalityState": {
                "nationality": "Colombiana",
                "nationalityStateCode": 1,
                "nationalityStateName": "No importa"
            },
            "affiliationInformation": {
                "medicalEntity": "algo1",
                "healthRegime": "algo2",
                "benefitPlan": "algo3",
                "socialStratum": "algoxxxx4"
            }
        };

        const response = await axios.post(
              'http://localhost:8080/patient',
              newPatient,
              {
                headers: {
                  Authorization: `Bearer ${authToken}`,
                },
              }
        );

        expect(response.status).to.equal(200);
    });
});