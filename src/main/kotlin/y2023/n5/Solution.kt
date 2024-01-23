package y2023.n5

import kotlin.math.pow

object Solution {

    fun part1(inputLines: List<String>): Long {
        val seedsToBePlanted = inputLines[0].split(":")[1].trim().split(" ").map(String::toLong).toList()
        return getMinLocationForGivenSeeds(seedsToBePlanted, inputLines)
    }

    fun part2(inputLines: List<String>): Int {
        // This is making me have a heap space problem
        var result = Long.MAX_VALUE
        val rangesToBePlanted = inputLines[0].split(":")[1].trim()
        rangesToBePlanted.split(" ").map(String::toLong).toList().zipWithNext().forEach {
            val minForGroup = getMinLocationForGivenSeeds((it.first until it.first+it.second).toList(), inputLines)
            if (result > minForGroup) result = minForGroup
        }
        return 1
    }

    private fun getMinLocationForGivenSeeds(originalSeeds: List<Long>, inputLines: List<String>): Long {
        val seedToSoilStart = inputLines.indexOf("seed-to-soil map:")
        val soilToFertilizerStart = inputLines.indexOf("soil-to-fertilizer map:")
        val fertilizerToWaterStart = inputLines.indexOf("fertilizer-to-water map:")
        val waterToLightStart = inputLines.indexOf("water-to-light map:")
        val lightToTemperatureStart = inputLines.indexOf("light-to-temperature map:")
        val temperatureToHumidityStart = inputLines.indexOf("temperature-to-humidity map:")
        val humidityToLocationStart = inputLines.indexOf("humidity-to-location map:")

        val seedToSoilMaps = inputLines.subList(seedToSoilStart+1, soilToFertilizerStart-1)
        val soilToFertilizerMaps = inputLines.subList(soilToFertilizerStart+1, fertilizerToWaterStart-1)
        val fertilizerToWaterMaps = inputLines.subList(fertilizerToWaterStart+1, waterToLightStart-1)
        val waterToLightMaps = inputLines.subList(waterToLightStart+1, lightToTemperatureStart-1)
        val lightToTemperatureMaps = inputLines.subList(lightToTemperatureStart+1, temperatureToHumidityStart-1)
        val temperatureToHumidityMaps = inputLines.subList(temperatureToHumidityStart+1, humidityToLocationStart-1)
        val humidityToLocationMaps = inputLines.subList(humidityToLocationStart+1, inputLines.size)

        val soil = convertSeedsThroughMap(originalSeeds, seedToSoilMaps)
        val fertilizer = convertSeedsThroughMap(soil, soilToFertilizerMaps)
        val water = convertSeedsThroughMap(fertilizer, fertilizerToWaterMaps)
        val light = convertSeedsThroughMap(water, waterToLightMaps)
        val temp = convertSeedsThroughMap(light, lightToTemperatureMaps)
        val humidity = convertSeedsThroughMap(temp, temperatureToHumidityMaps)
        val location = convertSeedsThroughMap(humidity, humidityToLocationMaps)

        return location.min()
    }

    private fun convertSeedsThroughMap(originalSeeds: List<Long>, converter: List<String>): List<Long> {
        val convertedSeeds = LongArray(size=originalSeeds.size)
        for ((idx, seed) in originalSeeds.withIndex()) {
            val correctMapIdx = converter.indexOfFirst {
                val sourceStart = it.split(" ")[1].toLong()
                val sourceEnd = sourceStart + (it.split(" ")[2].toLong() - 1)
                seed in sourceStart..sourceEnd
            }
            if (correctMapIdx != -1) {
                convertedSeeds[idx] = seed -
                        converter[correctMapIdx].split(" ")[1].toLong() +
                        converter[correctMapIdx].split(" ")[0].toLong()
            } else { convertedSeeds[idx] = seed }
        }
        return convertedSeeds.toList()
    }

}