// main.js
import { measure } from '../shared/shared.js';
import { Worker } from 'worker_threads';
import os from 'os';

const workerScript = new URL('./worker.js', import.meta.url);
const workerCount = os.cpus().length - 1;
const workers = [];

for (let c = 0; c < workerCount; c++) {
    workers.unshift(new Worker(workerScript));
    workers[0].on('message', ({ resolveId, result }) => promises[resolveId](result));
}

const promises = {};
let resolveId = 0;

function runTask(n, resolve) {
    promises[resolveId] = resolve;
    workers[ resolveId% workerCount].postMessage({ n, resolveId });
    resolveId++;
}

function work(n) {
    if (n < 29) {
        return new Promise(resolve => runTask(n, resolve));
    }

    return Promise.all([work(n - 1), work(n - 2)])
        .then(ar => ar[0] + ar[1]);
}

await measure(work);
console.log("thread count", workerCount);

for (const worker of workers) {
    worker.terminate();
}
