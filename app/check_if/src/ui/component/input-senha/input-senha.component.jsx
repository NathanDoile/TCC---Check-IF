import "./input-senha.component.css";
import olhoAbertoImg from "../../../assets/images/OlhoAberto-marrom.svg";
import olhoFechadoImg from "../../../assets/images/OlhoFechado-marrom.svg";
import { useState } from "react";

export function InputSenha({ legenda, name, handleChange }) {
  const [olhoAberto, setOlhoAberto] = useState(false);

  return (
    <span className="container-input-senha">
      {legenda}:
      <div className="input-senha-alterar">
        <input
          type={olhoAberto ? "text" : "password"}
          name={name}
          className="texto-input"
          required
          onChange={handleChange}
          autoComplete="off"
        />
        <img
          onClick={() => {
            setOlhoAberto(!olhoAberto);
          }}
          src={olhoAberto ? olhoAbertoImg : olhoFechadoImg}
          alt={"Olho"}
          className="imagem-input"
        />
      </div>
    </span>
  );
}
