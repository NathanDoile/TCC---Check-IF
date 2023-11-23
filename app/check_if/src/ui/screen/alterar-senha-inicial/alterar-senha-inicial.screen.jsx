import './alterar-senha-inicial.screen.css';
import { Borda, LogoInicial, TituloTelasIniciais, InputSenha, Botao } from '../../component';

export function TelaAlterarSenhaInicial() {

    return (
        <>
            <Borda posicao="superior" />

            <main className='main-alterar-senha-inicial'>

                <LogoInicial />

                <span className='container-form-alterar-senha-inicial'>

                    <TituloTelasIniciais>Alterar senha</TituloTelasIniciais>

                    <form className='form-alterar-senha-inicial'>

                        <InputSenha legenda="Senha atual" name="senhaAtual" handleChange={() => { }} />

                        <InputSenha legenda="Nova senha" name="novaSenha" handleChange={() => { }} />

                        <InputSenha legenda="Confirmar nova senha" name="confirmarNovaSenha" handleChange={() => { }} />

                        <Botao cor="laranja">Alterar</Botao>

                    </form>

                </span>

            </main>

            <Borda posicao="inferior" />
        </>
    )
}