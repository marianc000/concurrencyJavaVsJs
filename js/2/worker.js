// worker.js
import { parentPort } from 'worker_threads';
import { fibonacci } from '../shared/shared.js';

parentPort.on('message', ({ resolveId, n }) =>
    parentPort.postMessage({ resolveId, result: fibonacci(n) }));
