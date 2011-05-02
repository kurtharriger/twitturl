(defproject twitturl "1.0.0-SNAPSHOT"
  :description "FIXME: write"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring "0.3.7"]
                 [com.twinql.clojure/clj-apache-http "2.3.1"]
                 [hiccup "0.3.4"]]
  :dev-dependencies [[lein-ring "0.4.0"]]
  :ring {:handler twitturl.core/app})
