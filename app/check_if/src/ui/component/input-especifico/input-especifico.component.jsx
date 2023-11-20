import "./input-especifico.component.css";
import { useRef } from "react";

export function InputEspecifico({
  imagem,
  alt,
  type,
  name,
  placeholder,
  isRequired,
  handleChange,
  file,
}) {
  const inputRef = useRef(null);

  function onClickNuvem() {
    inputRef.current.click();
  }

  return (
    <>
      {type === "file" ? (
        <div className="input-login">
          <img
            src={imagem}
            alt={alt}
            className="imagem-input nuvem-input"
            onClick={onClickNuvem}
          />
          <input
            ref={inputRef}
            type={type}
            name={name}
            className="texto-input-file"
            onChange={handleChange}
            accept=".xslx, .xsl"
          />
          <span className="texto-input">
            {file !== "" ? file : placeholder}
          </span>
        </div>
      ) : (
        <div className="input-login">
          <img src={imagem} alt={alt} className="imagem-input" />
          <input
            type={type}
            name={name}
            placeholder={placeholder}
            className="texto-input"
            required={isRequired}
            onChange={handleChange}
            autoComplete="off"
          />
        </div>
      )}
    </>
  );
}
