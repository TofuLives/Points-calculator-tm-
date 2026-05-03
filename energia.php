<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="estilo.css">
    <!-- fonte das letras -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <title>Calcular Pegada de Carbono</title>
</head>
<body>
    
    <div class="topo w3-bar">
    <span class="w3-bar-item w3-xlarge">EcoCalc</span>
    <a href="paginainicial.php" class="w3-bar-item w3-button w3-right">Sair</a>
    </div>
    <div class="w3-container w3-padding-64">
    <div class="w3-content" style="max-width:700px">
    
    <div class="progresso">
    <div class="barra"></div>
    </div>

    <div class="card">
      <h2 class="titulo">Energia</h2>
      <p class="recado">
        Responda sobre seus hábitos de energia.
      </p>
    </div>
    
<div class="perguntas">
    <form action="resultado.php" method="post" class="questionario-form">

    <label>Quantas pessoas moram em sua residência?</label>
    <input type="number" min="1" placeholder="Ex: 3" class="questionario-input" required>

    <label>Qual o valor médio da conta de energia?</label>
    <input type="number" min="1" placeholder="Ex: 150" class="questionario-input" required>

    <label>Utiliza lâmpadas LED?</label>
    <select class="questionario-select" required>
    <option value="">Selecione</option>
    <option>Sim</option>
    <option>Não</option>
    </select>

    <label>Faz uso de energia renovável (placas solares)?</label>
    <select class="questionario-select" required>
    <option value="">Selecione</option>
    <option>Sim</option>
    <option>Não</option>
    </select>

<br>
    <button type="submit" class="botao" style= "text-decoration: none;">
    Próximo →
    </button>
    

</form>
</div>
    </div>
    </div>
    </div>
</body>
</html>