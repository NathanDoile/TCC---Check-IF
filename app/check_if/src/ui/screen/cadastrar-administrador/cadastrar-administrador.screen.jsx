import './cadastrar-administrador.screen.css';
import { Cabecalho, TituloTelasIniciais, Input, Botao } from '../../component';
import { useState } from 'react';
import { toast } from 'react-toastify';
import { useCadastrarAdministrador } from '../../../hooks';

export function TelaCadastrarAdministrador(){

    const {cadastrarAdministrador} = useCadastrarAdministrador();

    const [formInput, setFormInput] = useState({
        nome: "",
        siape: "",
        email: ""
    });

    function handleChange(event){
        const { name, value } = event.target;

        setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
    }

    async function cadastrar(event){
        event.preventDefault();

        if (
            formInput.nome === "" ||
            formInput.siape === "" ||
            formInput.email === ""
          ) {
            toast.error("Preencha todos os campos obrigatórios!");
          } else {
            await cadastrarAdministrador(formInput.nome, formInput.siape, formInput.email);
          }
    }

    return(
        <>

            <Cabecalho />

            <main className='main-cadastrar-adm'>

                <TituloTelasIniciais>Cadastrar administrador</TituloTelasIniciais>

                <form className='form-cadastrar-adm' onSubmit={cadastrar}>

                    <Input legenda="Nome completo" isObrigatorio name="nome" handleChange={handleChange} type="text" grande />

                    <Input legenda="Siape" isObrigatorio name="siape" handleChange={handleChange} type="text" grande />

                    <Input legenda="E-mail" isObrigatorio name="email" handleChange={handleChange} type="text" grande />

                    <Botao cor="laranja">Cadastrar</Botao>

                </form>

            </main>

        </>
    )
}