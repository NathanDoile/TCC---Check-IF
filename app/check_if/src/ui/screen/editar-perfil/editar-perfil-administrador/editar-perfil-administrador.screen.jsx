import './editar-perfil-administrador.screen.css';
import { Cabecalho, TituloTelasIniciais, Botao } from '../../../component';
import userImg from '../../../../assets/images/UserCirculo-marrom.svg';

export function TelaEditarPerfilAdministrador(){

    return(
        <>

            <Cabecalho />

            <main className='main-tela-editar-perfil-adm'>

                <TituloTelasIniciais>Editar informações do perfil</TituloTelasIniciais>

                <img src={userImg} alt='Usuário' className='icone-user-editar-perfil' />

                <form className='form-editar-perfil-adm'>

                    <label className='label-editar-perfil-adm'>Nome:
                        <input type='text' className='input-editar-adm'  name='nome' />
                    </label>

                    <label className='label-editar-perfil-adm'>E-mail:
                        <input type='text' className='input-editar-adm' name='email' />
                    </label>

                    <Botao cor="laranja">Salvar</Botao>
                </form>

            </main>
            
        </>
    )
}