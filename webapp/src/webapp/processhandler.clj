(ns webapp.processhandler
  ;; (:use [clojure.java.shell :only [sh]])
  (:require [clojure.core.async :as async]
            ;; [clojure.java.shell :as shell]
            )

  )

(def PROCESS-BIN "python -u /Users/nicola/repos/graph_web_external_proc/extprocess.py")


(defn ext-process-output-stream [bin]
  "lazy sequence of strings, one per line"
  (->> (.exec (Runtime/getRuntime) bin)
    .getInputStream
    clojure.java.io/reader
    line-seq))

(defn stream-channeller [stream chan]
  (map (partial async/>!! chan) stream))

(def shared-chan (async/chan 64))

(defn test-a []
  (let [stream (ext-process-output-stream PROCESS-BIN)]
    (async/go (doall (stream-channeller stream shared-chan)))))


(defn test-b []
  (while true (println (async/<!! shared-chan)))
  )
