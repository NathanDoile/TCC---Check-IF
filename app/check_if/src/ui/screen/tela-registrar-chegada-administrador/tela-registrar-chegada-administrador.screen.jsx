import './tela-registrar-chegada-administrador.screen.css';
import { Cabecalho, TituloTelasIniciais, Input } from '../../component';
import { useState } from 'react';

export function TelaRegistrarChegadaAdministrador() {

    const [formInput, setFormInput] = useState({
        professor: 0,
        disciplina: "",
        motivo: "",
        matricula: "",
    });

    const [outroMotivo, setOutroMotivo] = useState(false);

    return (
        <>
            <Cabecalho />

            <main className='main-tela-registrar-chegada-adm'>

                <TituloTelasIniciais>Registrar chegada atrasada</TituloTelasIniciais>

                <form>

                    <Input isSelect={false} legenda="Matrícula do aluno" isObrigatorio={true} name="matricula" handleChange={() => { }}
                        type="text" grande={true} />

                    <span>
                        <Input isSelect={true} legenda="Professor" isObrigatorio={true} name="professor" handleChange={() => { }}
                             />
                    </span>

                </form>

            </main>
        </>
    )
}