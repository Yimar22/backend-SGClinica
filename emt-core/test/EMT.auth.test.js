const { StatusCodes } = require('http-status-codes');
const { expect } = require('chai');
const axios = require('axios');

const urlBase = 'http://localhost:8080';

describe('Autenticación', () => {

    it('debería autenticar un usuario válido', async () => {

    const credentials = {
      username: 'admin',
      password: 'testpassword',
    };

    const response = await axios.post('http://localhost:8080/public/auth/login', credentials);
    expect(response.status).to.equal(StatusCodes.OK);
    expect(response.status).to.equal(200);
    expect(response.data).to.have.property('token');
    });

    it('no debería autenticar un usuario invalido', async () => {

        const credentials = {
          username: 'admin2',
          password: 'testpassword',
        };

        try {
          const response = await axios.post('http://localhost:3000/auth', credentials);

          expect.fail('Se esperaba que la autenticación fuera rechazada');
        } catch (error) {

        }
    });
});