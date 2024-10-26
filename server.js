const express = require('express');
const cors = require('cors');
const axios = require('axios');

const app = express();
const PORT = 8080;


const corsOptions = {
    origin: 'http://localhost:3000',
    methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
    allowedHeaders: ['Content-Type', 'Authorization'],
};

app.use(cors(corsOptions));
app.use(express.json());


const JAVA_BACKEND_URL = 'http://localhost:8081/api/users';


app.post('/api/users/register', async (req, res) => {
    const { username, password, isAdmin } = req.body;
    try {

        const response = await axios.post(`${JAVA_BACKEND_URL}/register?isAdmin=${isAdmin}`, {
            username,
            password,
        });

        res.status(200).json(response.data);
    } catch (error) {
        console.error('Ошибка регистрации пользователя:', error.response ? error.response.data : error.message);
        res.status(500).json({ message: 'Ошибка регистрации пользователя' });
    }
});


app.post('/api/users/authenticate', async (req, res) => {
    const { username, password } = req.body;

    try {

        const response = await axios.post(`${JAVA_BACKEND_URL}/authenticate`, {
            username,
            password,
        });

        res.status(200).json(response.data);
    } catch (error) {
        console.error('Ошибка аутентификации:', error.response ? error.response.data : error.message);
        res.status(401).json({ message: 'Неверное имя пользователя или пароль' });
    }
});

app.listen(PORT, () => {
    console.log(`Сервер запущен на http://localhost:${PORT}`);
});
