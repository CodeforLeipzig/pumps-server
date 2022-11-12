# Leipzig pumps management

## pumps server

 * this project is mainly maintained at [GitLab](https://gitlab.com/leipzig-pumps/pumps-server)
 * functional
   * pump management
     * edit pumps (location, checks, etc.)
     * share / upload pump images
   * tree management
     * visualize and analyse historic tree data (versioned in [giessdeibohm project](https://github.com/CodeforLeipzig/giessdeibohm))
       * show diffs (cut trees, changed tree attributes, etc.)
 * tech stack
   * [PostGIS](https://postgis.net) ((Geo) database)
   * [GeoServer](http://geoserver.org) (planned WMS (map tiles) / WFS service)
   * [SpringBoot](https://spring.io/projects/spring-boot) (REST + [GraphQL](https://netflix.github.io/dgs/))
   * [KeyCloak](https://www.keycloak.org) (planned authentication provider)
   * [GitLab CI](https://docs.gitlab.com/ee/ci/) (automated build, Docker registry, deployment to Kubernetes)
   * [Terraform](https://www.terraform.io) (planned cloud provision)
   * [OpenStack](https://www.openstack.org) (planned cloud platform)
   * [Kubernetes](https://kubernetes.io/) (planned cloud container platform)
   * [FugaCloud](https://fuga.cloud) (planned cloud platform)
 * See [deploy](https://gitlab.com/leipzig-pumps/deploy) for deployment documentation

## pumps frontend

 * this project is mainly maintained at [GitLab](https://gitlab.com/leipzig-pumps/reactive-pump)
 * functional
   * pump management
     * show on map
     * CRUD operations
     * upload images
   * tree management
     * show diffs between trees at different points in time (newly plant, cut off, changed attributes (corrected or newly planted at same location)) (something like [geojson-diff](https://github.com/benbalter/geojson-diff))
 * tech stack
   * [Svelte](https://svelte.dev)
   * [Leaflet](https://leafletjs.com)

## Charts
 * [Street tree count in Leipzig](https://datawrapper.dwcdn.net/IPrHb/8/)
 * [Street tree count in Leipzig splitted by districts](https://datawrapper.dwcdn.net/ifuls/2/)
 * [Street tree count changes April 2021 to March 2022 in Leipzig splitted by districts](https://datawrapper.dwcdn.net/uuNlj/1/)
 * [Street tree count in Leipzig splitted by species](https://datawrapper.dwcdn.net/fXwZM/1/)
 * [Street tree count changes April 2021 to March 2022 in Leipzig splitted by species](https://datawrapper.dwcdn.net/yUBUD/1/)
 * [Lost street trees between April 2021 and April 2022](https://sannsie.carto.com/builder/ea1519a7-a58d-46a1-bd3a-286218c357d8/embed)
 * [Replaced street trees between April 2021 and April 2022](https://sannsie.carto.com/builder/2ad5f54c-30b1-455b-84c7-9a51302523ea/embed)
 * [New street trees between January 2021 and April 2022](https://sannsie.carto.com/builder/5c4efdcf-0fd6-49f9-b8cb-511399eb3c5e/embed)