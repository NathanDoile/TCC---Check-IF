import { useState, useEffect } from "react";
import "./input.component.css";

export function Input({
  isSelect,
  legenda,
  isObrigatorio,
  name,
  handleChange,
  opcoes,
  values,
  type,
  grande,
}) {
  const [options, setOptions] = useState([]);

  useEffect(() => {
    setOptions([]);

    if (opcoes) {
      for (let i = 0; i < opcoes.length; i++) {
        setOptions((oldOptions) => [
          ...oldOptions,
          <option value={values[i]}>{opcoes[i]}</option>,
        ]);
      }
    }
  }, [opcoes, values]);

  return (
    <>
      {isSelect ? (
        <>
          <label className="label-input">
            <span>
              {legenda}:
              {isObrigatorio ? (
                <span className="input-obrigatorio">*</span>
              ) : null}
            </span>

            <select
              name={name}
              className={
                grande ? `caixa-input caixa-input-grande` : `caixa-input`
              }
              onChange={handleChange}
              required={isObrigatorio}
            >
              <option value={0}>Selecione</option>
              {options}
            </select>
          </label>
        </>
      ) : (
        <>
          <label className="label-input">
            <span>
              {legenda}:
              {isObrigatorio ? (
                <span className="input-obrigatorio">*</span>
              ) : null}
            </span>
            <input
              type={type}
              name={name}
              className={
                grande ? `caixa-input caixa-input-grande` : `caixa-input`
              }
              onChange={handleChange}
              required={isObrigatorio}
            />
          </label>
        </>
      )}
    </>
  );
}
