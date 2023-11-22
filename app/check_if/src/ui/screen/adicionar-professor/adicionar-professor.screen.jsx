import './adicionar-professor.screen.css';
import { Cabecalho, TituloTelasIniciais, Input, Botao } from '../../component';

export function TelaAdicionarProfessor(){

    return(
        <>

            <Cabecalho />

            <main className='main-adicionar-professor'>

                <TituloTelasIniciais>Adicionar professor</TituloTelasIniciais>

                <form className='form-adicionar-professor'>

                    <Input legenda="Nome completo" isObrigatorio name="nome" handleChange={() => {}} type="text" grande />

                    <Input legenda="Siape" isObrigatorio name="siape" handleChange={() => {}} type="text" grande />

                    <Input legenda="E-mail" isObrigatorio name="email" handleChange={() => {}} type="text" grande />

                    <Input legenda="Celular" name="celular" handleChange={() => {}} type="text" grande />

                    <Botao cor="laranja">Adicionar</Botao>
                    
                </form>

            </main>

        </>
    )
}