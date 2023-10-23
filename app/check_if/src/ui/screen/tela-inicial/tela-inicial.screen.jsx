import './tela-inicial.css';
import barra from '../../../assets/images/Barra-Home.svg';
import { Borda, LogoInicial, Botao } from '../../component';
import { useNavigate } from 'react-router-dom';

export function TelaInicial(){

    const navigate = useNavigate();

    function botaoLogin(){
        navigate("/login");
    }

    function botaoRegistrarChegada(){
        navigate("/registrar-chegada");
    }

    return(
        <section className='section-tela-inicial'>

            <Borda posicao='superior' />

            <main className = 'tela-inicial'>

                <LogoInicial />

                <h1 className = 'titulo-tela-inicial'>Boas vindas ao Check-IF</h1>

                <h2 className = 'descricao-botao-tela-inicial'>Registre sua chegada atrasada aqui!</h2>

                <Botao onClick={botaoRegistrarChegada} cor="verde">Registrar</Botao>

                <div className = "ou-barras-tela-inicial">

                    <img src={barra} className = 'barra-esquerda-tela-inicial' alt="barra" />

                    <p className = 'ou-central-tela-inicial'>Ou</p>

                    <img src={barra} className = 'barra-direita-tela-inicial' alt="barra" />

                </div>

                <h2 className = 'descricao-botao-tela-inicial'>Se você for usuário, realize seu login aqui!</h2>

                <Botao onClick={botaoLogin} cor="verde">Login</Botao>

            </main>
            
            <Borda posicao='inferior' />

        </section>
    );
}