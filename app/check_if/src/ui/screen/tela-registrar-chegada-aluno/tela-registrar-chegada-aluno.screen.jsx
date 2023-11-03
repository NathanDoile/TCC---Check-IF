import "./tela-registrar-chegada-aluno.screen.css";
import {
  Borda,
  LogoInicial,
  Botao,
  Input,
  TituloTelasIniciais,
} from "../../component";
import cameraIcone from "../../../assets/images/Camera-branco.svg";
import { toast } from "react-toastify";
import Webcam from "react-webcam";
import { useRef, useCallback, useState } from "react";
import { useLerCracha } from "../../../hooks";

export function TelaRegistrarChegadaAluno() {
  const { lerCracha } = useLerCracha();

  const [formInput, setFormInput] = useState({
    professor: 0,
    disciplina: "",
    motivo: "",
    matricula: "",
  });

  const [outroMotivo, setOutroMotivo] = useState(false);

  const [capturarCodigoBarras, setCapturarCodigoBarras] = useState(false);

  const webcamRef = useRef(null);

  const [imgSrc, setImgSrc] = useState(null);

  const [professores] = useState(["Selecione", "Roberto", "Lourenço"]);
  const [idsProfessores] = useState([0, 1, 2]);

  const motivos = [
    "Selecione",
    "Despertador não tocou",
    "Problema no trânsito",
    "Outro",
  ];
  const valuesMotivos = [...motivos];

  function handleChange(event) {
    const { name, value } = event.target;

    if (name === "motivo" && value === "Outro") {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: "" }));

      setOutroMotivo(true);
    } else if (name === "outroMotivo") {
      setFormInput((oldFormInput) => ({ ...oldFormInput, motivo: value }));
    } else if (name === "motivo" && value !== "Outro" && outroMotivo) {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));

      setOutroMotivo(false);
    } else {
      setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
    }
  }

  async function registrarChegada(event) {
    event.preventDefault();

    if (
      formInput.professor === 0 ||
      formInput.motivo === "Selecione" ||
      formInput.matricula === ""
    ) {
      toast.error("Preencha todos os campos obrigatórios!");
    } else {
      toast.success("OK!");
    }
  }

  const capture = useCallback(() => {
    const imageSrc = webcamRef.current.getScreenshot();
    
    setImgSrc(imageSrc);
  }, [webcamRef]);

  async function concluirLerCracha() {
    try {
      //const response = await lerCracha(atob(imgSrc.substring(23)));
      const response = await lerCracha(imgSrc.substring(23));
      console.log(response);

      setCapturarCodigoBarras(false);

      setFormInput((oldFormInput) => ({
        ...oldFormInput,
        matricula: response.matricula,
      }));
    } catch (error) {
      console.log(error);
      setImgSrc(null);
    }
  }

  return (
    <section className="section-registrar-chegada">
      <Borda posicao="superior" />

      <main className="main-registrar-chegada">
        {!capturarCodigoBarras ? (
          <>
            <LogoInicial />

            <TituloTelasIniciais>
              Registre sua chegada atrasada
            </TituloTelasIniciais>

            <span className="container-botao-camera">
              <Botao
                cor="verde"
                onClick={() => {
                  setCapturarCodigoBarras(true);
                  setImgSrc(null);
                }}
              >
                <img className="icone-camera" src={cameraIcone} alt="câmera" />
                <p className="legenda-botao-camera">Ler crachá</p>
              </Botao>
              <span className="input-obrigatorio">*</span>
            </span>

            <form
              className="form-registrar-chegada-atrasada"
              onSubmit={registrarChegada}
            >
              <span className="form-span-inputs">
                <Input
                  isSelect={true}
                  legenda={"Professor"}
                  isObrigatorio={true}
                  name={"professor"}
                  handleChange={handleChange}
                  opcoes={professores}
                  values={idsProfessores}
                />

                <Input
                  legenda={"Disciplina"}
                  name={"disciplina"}
                  handleChange={handleChange}
                  type={"text"}
                />
              </span>

              <span className="form-span-inputs">
                <Input
                  isSelect={true}
                  legenda={"Motivo"}
                  isObrigatorio={true}
                  name={"motivo"}
                  handleChange={handleChange}
                  opcoes={motivos}
                  values={valuesMotivos}
                />

                {outroMotivo ? (
                  <Input
                    legenda={"Outro"}
                    name={"outroMotivo"}
                    isObrigatorio={true}
                    handleChange={handleChange}
                    type={"text"}
                  />
                ) : null}
              </span>

              <Botao cor="amarelo">Registrar</Botao>
            </form>
          </>
        ) : null}
      </main>

      {capturarCodigoBarras ? (
        <div className="modal-camera">
          {imgSrc ? (
            <img src={imgSrc} alt="webcam" />
          ) : (
            <Webcam
              height={600}
              width={600}
              ref={webcamRef}
              screenshotFormat="image/jpeg"
              screenshotQuality={1}
            />
          )}

          {!imgSrc ? (
            <Botao cor="verde" onClick={capture}>
              Capturar foto
            </Botao>
          ) : (
            <>
              <Botao
                cor="verde"
                onClick={() => {
                  setImgSrc(null);
                }}
              >
                Tentar novamente
              </Botao>
              <Botao cor="amarelo" onClick={concluirLerCracha}>
                Concluir
              </Botao>
            </>
          )}
        </div>
      ) : null}

      <Borda posicao="inferior" />
    </section>
  );
}
