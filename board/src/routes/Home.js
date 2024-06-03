import React, { useState } from 'react';
import SockJsClient from 'react-stomp';
import './Home.css';

function Home() {
    const [nextSenha, setNextSenha] = useState(null);
    const [chamadasAnteriores, setChamadasAnteriores] = useState([]);

    const handleQueueData = (msg) => {
        const keys = Object.keys(msg);
        const lastKey = keys.length > 0 ? keys[keys.length - 1] : null;
        if (lastKey) {
            const senha = [lastKey, msg[lastKey]];
            setNextSenha(senha);
            const chamadas = keys.slice(0, -1).map(key => [key, msg[key]]);
            setChamadasAnteriores(chamadas);
        }
    };



    return (
        <div>
            <SockJsClient
                url='http://localhost:8080/ws'
                topics={['/topic']}
                onMessage={(msg) => {
                    handleQueueData(msg);
                }}
            />

            <div className="center">
                {nextSenha && (
                    <div className="card current-senha">
                        <div className="card-body">
                            <h1>Next Ticket</h1>
                            {isNaN(nextSenha[0][0]) ? (
                                <p className="card-text">
                                    {nextSenha[0]} - Clinic {nextSenha[1]}
                                </p>
                            ) : (
                                <p className="card-text">
                                    {nextSenha[0]} - Desk {nextSenha[1]}
                                </p>
                            )}
                        </div>
                    </div>
                )}
                {chamadasAnteriores.length > 0 && (
                    <div className="card chamadas-anteriores">
                        <div className="card-body">
                            <h2>Previous tickets</h2>
                            <ul className="card-text list-group list-group-flush">
                                {chamadasAnteriores.map(([key, value]) => (
                                    <li key={key} className="list-group-item">
                                        {isNaN(key[0]) ? (
                                            <span>{key} - Clinic {value}</span>
                                        ) : (
                                            <span>{key} - Desk {value}</span>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                )}
            </div>

        </div>
    );
}

export default Home;