import "./notificacao.component.css";
import iconeWhatsapp from "../../../assets/images/Whatsapp-marrom.svg";
import iconeEmail from "../../../assets/images/Email-marrom.svg";
import { useState } from "react";

export function Notificacao({
  nome,
  turma,
  matricula,
  numero,
  email,
  notificacaoNumero,
  notificacaoEmail,
}) {
  const [formInput, setFormInput] = useState({
    whatsapp: notificacaoNumero,
    email: notificacaoEmail,
  });

  function handleChange(event) {
    const { name, checked } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: checked }));
  }

  return (
    <div className="div-notificacao">
      <span className="notificacao-nome">{nome}</span>
      <span className="notificacao-turma">{turma}</span>
      <span className="notificacao-matricula">{matricula}</span>
      <span className="notificacao-notificacoes">
        <div className="dados-notificacao-notificacao">
          <img
            src={iconeWhatsapp}
            alt="WhatsApp"
            className="icone-notificacao-notificacao"
          />

          <img
            src={iconeEmail}
            alt="E-mail"
            className="icone-notificacao-notificacao"
          />
        </div>

        <div className="dados-notificacao-notificacao dados-notificacao-especifico">
          <span>{numero}</span>

          <span>{email}</span>
        </div>

        <form className="dados-notificacao-notificacao dados-notificacao-especifico">
          <label className="switch">
            <input
              type="checkbox"
              className="input-notificacao-notificacao"
              name="whatsapp"
              onChange={handleChange}
              checked={formInput.whatsapp}
            />
            <span className="slider round"></span>
          </label>

          <label className="switch">
            <input
              type="checkbox"
              className="input-notificacao-notificacao"
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
