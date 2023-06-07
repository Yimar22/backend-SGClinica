const { StatusCodes } = require('http-status-codes');
const { expect } = require('chai');
const axios = require('axios');
const dotenv = require("dotenv");

const urlBase = 'http://localhost:8080';

describe('User', () => {

    let authToken;
    let createdUserId;

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

    it('debería crear un nuevo usuario', async () => {

        const newUser = {

            "username" : "diego",
            "password" : "asd"
        };

        const response = await axios.post(
              'http://localhost:8080/user',
              newUser,
              {
                headers: {
                  Authorization: `Bearer ${authToken}`,
                },
              }
        );

        expect(response.status).to.equal(200);
    });

    it('debería listar todos los usuarios', async () => {

        const response = await axios.get(
            'http://localhost:8080/user',
            {
                headers: {

                    Authorization: `Bearer ${authToken}`,
                },
            }
        );

        expect(response.status).to.equal(200);
    });

    it('debería obtener los detalles de un usuario existente', async () => {
        const config = {
            headers: {
                Authorization: `Bearer ${authToken}`,
            },
        };

        const response = await axios.get('http://localhost:8080/user/diego', config);

        expect(response.status).to.equal(200);
        expect(response.data.username).to.equal("diego");
    });

    it('debería eliminar un usuario existente', async () => {
        const config = {
            headers: {
                Authorization: `Bearer ${authToken}`,
            },
        };

        const response = await axios.delete('http://localhost:8080/user/diego', config);

        expect(response.status).to.equal(200);
    });

});
