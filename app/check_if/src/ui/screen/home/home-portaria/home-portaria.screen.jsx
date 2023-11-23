import './home-portaria.screen.css';
import { Cabecalho, TituloTelasIniciais, Navbar, SaidaAntecipada } from '../../../component';

export function TelaHomePortaria() {

    return (
        <>

            <Cabecalho />

            <main className='main-home-portaria'>

                <TituloTelasIniciais>Solicitações de saídas antecipadas</TituloTelasIniciais>

                <div className='conteudo-saidas-antecipadas'>

                    <SaidaAntecipada nome="Nathan de Souza Doile" turma="4K" matricula="078790INFQ" data="23/11/2023"
                        hora="11:45" />

                    <SaidaAntecipada nome="Emily Aparecida da Silveira Eberhardt" turma="4I" matricula="078630INFQ" data="23/11/2023"
                        hora="11:45" />

                </div>

                <Navbar paginaAtual={0} numeroPaginas={1} alterarPagina={() => { }} />

            </main>

        </>
    )
}