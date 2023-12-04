import "./botao.component.css";
import loading from '../../../assets/images/loading.svg';

export function Botao({ cor, onClick, children, carregando }) {

  return (
    <>
      {cor === "verde" ? (
        <button className="botao verde" onClick={onClick}>
          {carregando ? <img src={loading} className="loading-botao" /> : children}
        </button>
      ) : (
        <button className="botao amarelo" onClick={onClick}>
          {carregando ? <img src={loading} className="loading-botao" /> : children}
        </button>
      )}
    </>
  );
}
