import './professor.component.css';
import iconeWhatsapp from '../../../assets/images/Whatsapp-marrom.svg';
import iconeEmail from '../../../assets/images/Email-marrom.svg';
import { useState } from 'react';

export function Professor({ nome, siape, numero, email, notificacaoNumero, notificacaoEmail }) {

    const [formInput, setFormInput] = useState({
        whatsapp: notificacaoNumero,
        email: notificacaoEmail
    })

    function handleChange(event){
        const { name, checked } = event.target;

        setFormInput((oldFormInput) => ({...oldFormInput, [name]:checked}))
    }

    return (
        <div className="div-professor">
            <span className="professor-nome">{nome}</span>
            <span className="professor-siape">{siape}</span>
            <span className="professor-notificacoes">

                <div className='dados-notificacao-professor'>

                    <img src={iconeWhatsapp} alt='WhatsApp' className='icone-notificacao-professor' />

                    <img src={iconeEmail} alt='E-mail' className='icone-notificacao-professor' />

                </div>

                <div className='dados-notificacao-professor dados-professor-especifico'>

                    <span>{numero}</span>

                    <span>{email}</span>

                </div>

                <form className='dados-notificacao-professor dados-professor-especifico'>

                    <label className='switch'>
                        <input type="checkbox" className='input-professor-notificacao' name='whatsapp' 
                            onChange={handleChange} checked={formInput.whatsapp} />
                        <span className='slider round'></span>
                    </label>

                    <label className='switch'>
                        <input type="checkbox" className='input-professor-notificacao' name='email' 
                            onChange={handleChange} checked={formInput.email} />
                        <span className='slider round'></span>
                    </label>

                </form>

            </span>
        </div>
    )
}