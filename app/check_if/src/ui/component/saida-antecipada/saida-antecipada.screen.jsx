import "./saida-antecipada.screen.css";
import confirmarSaidaImg from "../../../assets/images/Confirmar.svg";
import cancelarSaidaImg from "../../../assets/images/Cancelar.svg";
import { useConfirmarSaidaAntecipada } from "../../../hooks";

export function SaidaAntecipada({ nome, turma, matricula, data, hora, id, recarregar }) {

  const { confirmarSaidaAntecipada } = useConfirmarSaidaAntecipada();

  async function confirmar(saiu){
    await confirmarSaidaAntecipada(id, saiu);

    recarregar();
  }

  return (
    <div className="div-saida-antecipada">
      <span className="saida-antecipada-nome">{nome}</span>
      <span className="saida-antecipada-turma">{turma}</span>
      <span className="saida-antecipada-matricula">{matricula}</span>
      <span className="saida-antecipada-data">{data}</span>
      <span className="saida-antecipada-hora">{hora}</span>
      <img
        className="saida-antecipada-icones"
        src={confirmarSaidaImg}
        alt="Confirmar"
        onClick={() => {confirmar(true)}}
      />
      <img
        className="saida-antecipada-icones"
        src={cancelarSaidaImg}
        alt="Cancelar"
        onClick={() => {confirmar(false)}}
      />
    </div>
  );
}
