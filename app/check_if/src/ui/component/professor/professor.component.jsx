import "./professor.component.css";
import iconeTelegram from "../../../assets/images/telegram-marrom.svg";
import iconeEmail from "../../../assets/images/Email-marrom.svg";
import { useState, useEffect } from "react";
import { useAlterarNotificacaoProfessor } from '../../../hooks';
import { toast } from "react-toastify";
import loading from '../../../assets/images/loading.svg';

export function Professor({
  nome,
  siape,
  email,
  notificacaoEmail,
  id
}) {

  const [carregando, setCarregando] = useState(false);

  const [formInput, setFormInput] = useState({
    email: notificacaoEmail,
  });

  const { alterarNotificacaoProfessor } = useAlterarNotificacaoProfessor();

  async function handleChange(event) {

    const { name, checked } = event.target;

    try{

      setCarregando(true);
  
      await alterarNotificacaoProfessor(id);

      setCarregando(false);

      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: checked }));

    }
    catch(error){

      toast.error(error);

      setCarregando(false);
    }

  }

  useEffect(() => {
    setFormInput({email: notificacaoEmail})
  }, [notificacaoEmail])

  return (
    <div className="div-professor">
      <span className="professor-nome">{nome}</span>
      <span className="professor-siape">{siape}</span>
      <span className="professor-notificacoes">
        <div className="dados-notificacao-professor">

          <img
            src={iconeEmail}
            alt="E-mail"
            className="icone-notificacao-professor"
          />

        </div>

        <div className="dados-notificacao-professor dados-professor-especifico">

          <span>{email}</span>

        </div>

        <form className="dados-notificacao-professor dados-professor-especifico">

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
      {carregando ? <img src={loading} className="loading-lista" /> : null}
      
    </div>
  );
}
