(ns leiningen.new.compojure
  (:use [leiningen.new.templates :only [renderer sanitize year ->files]]
        [leinjacker.utils :only [lein-generation]]))

(def project-file
  (if (= (lein-generation) 2)
    "project_lein2.clj"
    "project_lein1.clj"))

(defn compojure
  "Create a new Compojure project"
  [name]
  (let [data {:name name
              :sanitized (sanitize name)
              :year (year)}
        render #((renderer "compojure") % data)]
    (println "Generating a lovely new Compojure project named" (str name "..."))
    (->files data
             [".gitignore"  (render "gitignore")]
             ["project.clj" (render project-file)]
             ["README.md"   (render "README.md")]
             ["src/{{sanitized}}/handler.clj"      (render "handler.clj")]
             ["src/{{sanitized}}/server.clj"       (render "server.clj")]
             ["src/{{sanitized}}/common.clj" (render "common.clj")]
             ["resources/css/screen.css" (render "screen.css")]
             "resources/js"
             "resources/img"
             "src/{{sanitized}}/models"
             ["test/{{sanitized}}/test/handler.clj" (render "handler_test.clj")])))
