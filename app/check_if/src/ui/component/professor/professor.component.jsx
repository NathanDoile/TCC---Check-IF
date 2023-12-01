import "./professor.component.css";
import iconeTelegram from "../../../assets/images/telegram-marrom.svg";
import iconeEmail from "../../../assets/images/Email-marrom.svg";
import { useState } from "react";
import { useAlterarNotificacaoProfessor } from '../../../hooks';
import { toast } from "react-toastify";

export function Professor({
  nome,
  siape,
  numero,
  email,
  notificacaoNumero,
  notificacaoEmail,
  id
}) {

  const [formInput, setFormInput] = useState({
    whatsapp: notificacaoNumero,
    email: notificacaoEmail,
  });

  const { alterarNotificacaoProfessor } = useAlterarNotificacaoProfessor();

  async function handleChange(event) {

    const { name, checked } = event.target;

    try{
  
      await alterarNotificacaoProfessor(id, name);

      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: checked }));

    }
    catch(error){

      toast.error(error);
    }

  }

  return (
    <div className="div-professor">
      <span className="professor-nome">{nome}</span>
      <span className="professor-siape">{siape}</span>
      <span className="professor-notificacoes">
        <div className="dados-notificacao-professor">
          <img
            src={iconeTelegram}
            alt="WhatsApp"
            className="icone-notificacao-professor"
          />

          <img
            src={iconeEmail}
            alt="E-mail"
            className="icone-notificacao-professor"
          />
        </div>

        <div className="dados-notificacao-professor dados-professor-especifico">
          <span>{numero}</span>

          <span>{email}</span>
        </div>

        <form className="dados-notificacao-professor dados-professor-especifico">
          <label className="switch">
            <input
              type="checkbox"
              className="input-professor-notificacao"
              name="whatsapp"
              onChange={handleChange}
              checked={formInput.whatsapp}
            />
            <span className="slider round"></span>
          </label>

          <label className="switch">
            <input
              type="checkbox"
              className="input-professor-notificacao"
              name="email"
              onChange={handleChange}
              checked={formInput.email}
            />
            <span className="slider round"></span>
          </label>
        </form>
      </span>
    </div>
  );
}
