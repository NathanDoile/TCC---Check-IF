import './alterar-senha-padrao.screen.css';
import { Cabecalho, TituloTelasIniciais, InputSenha, Botao } from '../../component';

export function TelaAlterarSenhaPadrao(){

    return(
        <>

            <Cabecalho />

            <main className='main-tela-alterar-senha-padrao'>

                <TituloTelasIniciais>Alterar senha</TituloTelasIniciais>

                <form className='form-alterar-senha-padrao'>

                    <InputSenha legenda="Senha atual" name="senhaAtual" handleChange={() => {}} />

                    <InputSenha legenda="Nova senha" name="novaSenha" handleChange={() => {}} />

                    <InputSenha legenda="Confirmar nova senha" name="confirmarNovaSenha" handleChange={() => {}} />

                    <Botao cor="laranja">Alterar</Botao>
                </form>

            </main>

        </>
    )
}