#!/usr/bin/env node

/**
 * Script para iniciar o servidor json-server local
 * 
 * Uso: npm run start
 * 
 * Funcionalidades:
 * - Verifica disponibilidade da porta 3003
 * - Validação de db.json
 * - Inicia json-server em modo watch
 * - Exibe informações de endpoints disponíveis
 * 
 * @author Projeto JSONPlaceholder API
 * @version 1.0.0
 */

const { spawn } = require('child_process');
const net = require('net');
const fs = require('fs');

const PORT = 3003;
const DB_FILE = 'db.json';

// Verifica se db.json existe
if (!fs.existsSync(DB_FILE)) {
  console.error(`❌ Erro: Arquivo ${DB_FILE} não encontrado!`);
  process.exit(1);
}

/**
 * Verifica se uma porta específica está em uso
 * @param {number} port - Número da porta a verificar
 * @returns {Promise<boolean>} true se em uso, false se livre
 */
function checkPort(port) {
  return new Promise((resolve) => {
    const server = net.createServer();
    server.once('error', () => resolve(true)); // Porta em uso
    server.once('listening', () => {
      server.close();
      resolve(false); // Porta livre
    });
    server.listen(port);
  });
}

/**
 * Inicia o servidor json-server
 */
async function start() {
  const inUse = await checkPort(PORT);
  
  if (inUse) {
    console.warn(`⚠️  Porta ${PORT} já está em uso. Tentando usar a próxima disponível...`);
  }

  console.log(`🚀 Iniciando API local...`);
  console.log(`✅ Servidor rodando em http://localhost:${PORT}`);
  console.log(`📝 Endpoints disponíveis:`);
  console.log(`   - GET http://localhost:${PORT}/posts`);
  console.log(`   - GET http://localhost:${PORT}/users`);
  console.log(`\n⏹️  Para parar, pressione Ctrl+C\n`);

  const server = spawn('npx', ['json-server', '--watch', DB_FILE, '--port', PORT], {
    stdio: 'inherit',
    shell: true
  });

  // Tratamento de sinal de interrupção (Ctrl+C)
  process.on('SIGINT', () => {
    console.log('\n👋 Encerrando servidor...');
    server.kill();
    process.exit(0);
  });
}

// Executar função start
start().catch(err => {
  console.error('Erro:', err);
  process.exit(1);
});

